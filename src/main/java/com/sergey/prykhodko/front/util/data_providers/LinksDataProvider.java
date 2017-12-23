package com.sergey.prykhodko.front.util.data_providers;

import com.sergey.prykhodko.model.order.suborder.Link;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class LinksDataProvider<T extends Link, S> extends SortableDataProvider<T, S>{

    private List<T> data;
    private S sortBy;

    public LinksDataProvider(List<T> data, S sortBy){
        this.data = data;
        this.sortBy = sortBy;
        setSort(sortBy, SortOrder.ASCENDING);
    }



    @Override
    public Iterator<? extends T> iterator(long first, long count) {
        Collections.sort(data, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                int dir = getSort().isAscending() ? 1 : -1;
                if ("id".equals(getSort().getProperty())) {
                    return dir * (o1.getId().compareTo(o2.getId()));
                } else {
                    return 0;
                }
            }
        });
        return data.subList((int)first, (int)Math.min(first + count, data.size())).iterator();
    }

    @Override
    public long size() {
        return data.size();
    }

    @Override
    public IModel<T> model(T object) {
        return Model.of(object);
    }
}
