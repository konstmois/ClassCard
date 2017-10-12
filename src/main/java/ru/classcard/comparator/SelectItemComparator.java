package ru.classcard.comparator;

import javax.faces.model.SelectItem;
import java.util.Comparator;

public class SelectItemComparator implements Comparator<SelectItem> {

    @Override
    public int compare(SelectItem sItem1, SelectItem sItem2) {
        return sItem1.getLabel().compareTo(sItem2.getLabel());
    }
}
