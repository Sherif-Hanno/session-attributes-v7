package com.company.sattr7.web.screens;

import com.company.sattr7.entity.BusinessUser;
import com.company.sattr7.entity.Company;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.mainwindow.AppWorkArea;
import com.haulmont.cuba.gui.components.mainwindow.FoldersPane;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.WebConfig;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.haulmont.cuba.web.widgets.CubaHorizontalSplitPanel;
import com.vaadin.server.Sizeable;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Created by Aleksey Stukalov on 06.03.2020.
 */


@UiController("extMainScreen")
@UiDescriptor("ext-main-screen.xml")
public class ExtMainScreen extends MainScreen implements Window.HasFoldersPane {
    @Inject
    private SplitPanel foldersSplit;
    @Inject
    private FoldersPane foldersPane;
    @Inject
    private AppWorkArea workArea;
    @Inject
    private WebConfig webConfig;
    @Inject
    private CollectionLoader<Company> companyDl;
    @Inject
    private UserSession userSession;
    @Inject
    private CollectionContainer<Company> companyDc;
    @Inject
    private DataManager dataManager;
    @Inject
    private LookupField<Company> companyLookup;

    public ExtMainScreen() {
        addInitListener(this::initLayout);
    }

    protected void initLayout(@SuppressWarnings("unused") InitEvent event) {
        if (webConfig.getFoldersPaneEnabled()) {
            if (webConfig.getFoldersPaneVisibleByDefault()) {
                foldersSplit.setSplitPosition(webConfig.getFoldersPaneDefaultWidth(), SizeUnit.PIXELS);
            } else {
                foldersSplit.setSplitPosition(0);
            }
            CubaHorizontalSplitPanel vSplitPanel = (CubaHorizontalSplitPanel) WebComponentsHelper.unwrap(foldersSplit);
            vSplitPanel.setDefaultPosition(webConfig.getFoldersPaneDefaultWidth() + "px");
            vSplitPanel.setMaxSplitPosition(50, Sizeable.Unit.PERCENTAGE);
            vSplitPanel.setDockable(true);
        } else {
            foldersPane.setEnabled(false);
            foldersPane.setVisible(false);
            foldersSplit.remove(workArea);
            int foldersSplitIndex = getWindow().indexOf(foldersSplit);
            getWindow().remove(foldersSplit);
            getWindow().add(workArea, foldersSplitIndex);
            getWindow().expand(workArea);
        }
    }

    @Nullable
    @Override
    public FoldersPane getFoldersPane() {
        return foldersPane;
    }

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        companyDl.load();

        BusinessUser businessUser = userSession.getAttribute("businessUser");
        if (businessUser != null) {
            companyLookup.setValue(businessUser.getCompany());
        }
    }

    @Subscribe("companyLookup")
    public void onCompanyLookupValueChange(HasValue.ValueChangeEvent<Company> event) {
        BusinessUser businessUser = userSession.getAttribute("businessUser");
        if (businessUser == null)
            return;
        businessUser.setCompany(event.getValue());
        dataManager.commit(businessUser);
    }
}