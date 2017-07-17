package com.filecorn.sdk.java;

import java.util.Collections;
import java.util.List;

/**
 * @author omidp
 *
 */
public class FolderResponse
{

    private List<Item> items;
    private long itemCount;

    public FolderResponse(List<Item> items, long itemCount)
    {
        this.items = items;
        this.itemCount = itemCount;
    }

    public List<Item> getItems()
    {
        return Collections.unmodifiableList(items);
    }


    public long getItemCount()
    {
        return itemCount;
    }


}
