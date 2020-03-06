package com.company.sattr7.web.screens.businessuser;

import com.haulmont.cuba.gui.screen.*;
import com.company.sattr7.entity.BusinessUser;


/**
 * Created by Aleksey Stukalov on 06.03.2020.
 */

@UiController("sattr7_BusinessUser.browse")
@UiDescriptor("business-user-browse.xml")
@LookupComponent("businessUsersTable")
@LoadDataBeforeShow
public class BusinessUserBrowse extends StandardLookup<BusinessUser> {
}