package com.company.sattr7.web.screens.businessuser;

import com.company.sattr7.entity.BusinessUser;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;


/**
 * Created by Aleksey Stukalov on 06.03.2020.
 */

@UiController("sattr7_BusinessUser.browse")
@UiDescriptor("business-user-browse.xml")
@LookupComponent("businessUsersTable")
@LoadDataBeforeShow
public class BusinessUserBrowse extends StandardLookup<BusinessUser> {
    @Inject
    private CollectionContainer<BusinessUser> businessUsersDc;
    @Inject
    private DataContext dataContext;
    @Inject
    private GroupTable<BusinessUser> businessUsersTable;
    @Inject
    private Notifications notifications;

    @Install(to = "pickUserDlg", subject = "dialogResultHandler")
    private void pickUserDlgDialogResultHandler(InputDialog.InputDialogResult result) {
        if (result.getCloseActionType().equals(InputDialog.InputDialogResult.ActionType.CANCEL))
            return;

        User user = result.getValue("user");
        assert user != null;

        BusinessUser businessUser = BusinessUser.ofUser(user);
        if (businessUser == null) {
            notifications.create()
                .withCaption("There is no business user liked with " + user.getLogin())
                .show();

            return;
        }

        BusinessUser dcItem = businessUsersDc.getItemOrNull(businessUser.getId());
        //if not in the page
        if (dcItem == null) {
            dcItem = dataContext.merge(businessUser);
            businessUsersDc.replaceItem(dcItem);
        }
        businessUsersTable.setSelected(dcItem);
    }

}