package com.company.sattr7.web.screens.businessuser;

import com.company.sattr7.entity.BusinessUser;
import com.haulmont.cuba.gui.screen.*;


/**
 * Created by Aleksey Stukalov on 06.03.2020.
 */

@UiController("sattr7_BusinessUser.edit")
@UiDescriptor("business-user-edit.xml")
@EditedEntityContainer("businessUserDc")
@LoadDataBeforeShow
public class BusinessUserEdit extends StandardEditor<BusinessUser> {
}