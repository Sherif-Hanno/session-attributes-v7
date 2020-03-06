package com.company.sattr7.web.screens.company;

import com.haulmont.cuba.gui.screen.*;
import com.company.sattr7.entity.Company;


/**
 * Created by Aleksey Stukalov on 06.03.2020.
 */

@UiController("sattr7_Company.edit")
@UiDescriptor("company-edit.xml")
@EditedEntityContainer("companyDc")
@LoadDataBeforeShow
public class CompanyEdit extends StandardEditor<Company> {
}