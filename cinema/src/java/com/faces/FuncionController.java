package com.faces;

import com.modelo.Funcion;
import com.faces.util.JsfUtil;
import com.faces.util.PaginationHelper;
import com.facade.FuncionFacade;

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

@ManagedBean(name = "funcionController")
@SessionScoped
public class FuncionController implements Serializable {

    private Funcion current;
    private DataModel items = null;
    @EJB
    private com.facade.FuncionFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public FuncionController() {
    }

    public Funcion getSelected() {
        if (current == null) {
            current = new Funcion();
            current.setFuncionPK(new com.modelo.FuncionPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private FuncionFacade getFacade() {
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
        current = (Funcion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Funcion();
        current.setFuncionPK(new com.modelo.FuncionPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getFuncionPK().setIdSala(current.getSala().getSalaPK().getIdSala());
            current.getFuncionPK().setIdPelicula(current.getPelicula().getIdPelicula());
            current.getFuncionPK().setIdSede(current.getSede().getIdSede());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FuncionCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Funcion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getFuncionPK().setIdSala(current.getSala().getSalaPK().getIdSala());
            current.getFuncionPK().setIdPelicula(current.getPelicula().getIdPelicula());
            current.getFuncionPK().setIdSede(current.getSede().getIdSede());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FuncionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Funcion) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FuncionDeleted"));
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

    @FacesConverter(forClass = Funcion.class)
    public static class FuncionControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FuncionController controller = (FuncionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "funcionController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.modelo.FuncionPK getKey(String value) {
            com.modelo.FuncionPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.modelo.FuncionPK();
            key.setIdSede(Integer.parseInt(values[0]));
            key.setIdPelicula(Integer.parseInt(values[1]));
            key.setIdSala(Integer.parseInt(values[2]));
            key.setFecha(java.sql.Date.valueOf(values[3]));
            return key;
        }

        String getStringKey(com.modelo.FuncionPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdSede());
            sb.append(SEPARATOR);
            sb.append(value.getIdPelicula());
            sb.append(SEPARATOR);
            sb.append(value.getIdSala());
            sb.append(SEPARATOR);
            sb.append(value.getFecha());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Funcion) {
                Funcion o = (Funcion) object;
                return getStringKey(o.getFuncionPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Funcion.class.getName());
            }
        }

    }

}
