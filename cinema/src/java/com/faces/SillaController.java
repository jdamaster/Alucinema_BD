package com.faces;

import com.modelo.Silla;
import com.faces.util.JsfUtil;
import com.faces.util.PaginationHelper;
import com.facade.SillaFacade;

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

@ManagedBean(name = "sillaController")
@SessionScoped
public class SillaController implements Serializable {

    private Silla current;
    private DataModel items = null;
    @EJB
    private com.facade.SillaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public SillaController() {
    }

    public Silla getSelected() {
        if (current == null) {
            current = new Silla();
            current.setSillaPK(new com.modelo.SillaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private SillaFacade getFacade() {
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
        current = (Silla) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Silla();
        current.setSillaPK(new com.modelo.SillaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getSillaPK().setIdSala(current.getSala().getSalaPK().getIdSala());
            current.getSillaPK().setIdSede(current.getSede().getIdSede());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SillaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Silla) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getSillaPK().setIdSala(current.getSala().getSalaPK().getIdSala());
            current.getSillaPK().setIdSede(current.getSede().getIdSede());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SillaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Silla) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SillaDeleted"));
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

    @FacesConverter(forClass = Silla.class)
    public static class SillaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SillaController controller = (SillaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sillaController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.modelo.SillaPK getKey(String value) {
            com.modelo.SillaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.modelo.SillaPK();
            key.setIdSilla(Integer.parseInt(values[0]));
            key.setIdSala(Integer.parseInt(values[1]));
            key.setIdSede(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(com.modelo.SillaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdSilla());
            sb.append(SEPARATOR);
            sb.append(value.getIdSala());
            sb.append(SEPARATOR);
            sb.append(value.getIdSede());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Silla) {
                Silla o = (Silla) object;
                return getStringKey(o.getSillaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Silla.class.getName());
            }
        }

    }

}
