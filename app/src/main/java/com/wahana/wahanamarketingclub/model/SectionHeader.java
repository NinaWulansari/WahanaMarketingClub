package com.wahana.wahanamarketingclub.model;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

/**
 * Created by wahana on 2/27/18.
 */

public class SectionHeader implements Section<CustomerProspectIndex> {

    List<CustomerProspectIndex> childList;
    String sectionText;

    public SectionHeader(List<CustomerProspectIndex> childList, String sectionText) {
        this.childList = childList;
        this.sectionText = sectionText;
    }

    @Override
    public List<CustomerProspectIndex> getChildItems() {
        return childList;
    }

    public String getSectionText() {
        return sectionText;
    }
}
