package com.faces;

import com.modelo.PuntoDeVenta;
import com.faces.util.JsfUtil;
import com.faces.util.PaginationHelper;
import com.facade.PuntoDeVentaFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "puntoDeVentaController")
@SessionScoped
public class PuntoDeVentaController implements Serializable {

    private PuntoDeVenta current;
    private DataModel items = null;
    @EJB
    private com.facade.PuntoDeVentaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PuntoDeVentaController() {
    }

    public PuntoDeVenta getSelected() {
        if (current == null) {
            current = new PuntoDeVenta();
            current.setPuntoDeVentaPK(new com.modelo.PuntoDeVentaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private PuntoDeVentaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (PuntoDeVenta) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PuntoDeVenta();
        current.setPuntoDeVentaPK(new com.modelo.PuntoDeVentaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getPuntoDeVentaPK().setIdSede(current.getSede().getIdSede());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PuntoDeVentaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (PuntoDeVenta) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getPuntoDeVentaPK().setIdSede(current.getSede().getIdSede());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PuntoDeVentaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PuntoDeVenta) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PuntoDeVentaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = PuntoDeVenta.class)
    public static class PuntoDeVentaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PuntoDeVentaController controller = (PuntoDeVentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "puntoDeVentaController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.modelo.PuntoDeVentaPK getKey(String value) {
            com.modelo.PuntoDeVentaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.modelo.PuntoDeVentaPK();
            key.setIdSede(Integer.parseInt(values[0]));
            key.setIdPunto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.modelo.PuntoDeVentaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdSede());
            sb.append(SEPARATOR);
            sb.append(value.getIdPunto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PuntoDeVenta) {
                PuntoDeVenta o = (PuntoDeVenta) object;
                return getStringKey(o.getPuntoDeVentaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PuntoDeVenta.class.getName());
            }
        }

    }

}
