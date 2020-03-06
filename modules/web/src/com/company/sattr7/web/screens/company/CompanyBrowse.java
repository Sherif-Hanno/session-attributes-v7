package com.company.sattr7.web.screens.company;

import com.haulmont.cuba.gui.screen.*;
import com.company.sattr7.entity.Company;


/**
 * Created by Aleksey Stukalov on 06.03.2020.
 */

@UiController("sattr7_Company.browse")
@UiDescriptor("company-browse.xml")
@LookupComponent("companiesTable")
@LoadDataBeforeShow
public class CompanyBrowse extends StandardLookup<Company> {
}