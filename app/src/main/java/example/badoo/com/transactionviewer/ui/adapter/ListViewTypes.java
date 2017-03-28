package example.badoo.com.transactionviewer.ui.adapter;

import static example.badoo.com.transactionviewer.ui.adapter.ListViewTypes.VIEW_HOLDER_HEADER;
import static example.badoo.com.transactionviewer.ui.adapter.ListViewTypes.VIEW_HOLDER_ITEM;

import android.support.annotation.IntDef;

@IntDef({ VIEW_HOLDER_ITEM, VIEW_HOLDER_HEADER })
@interface ListViewTypes {
    int VIEW_HOLDER_HEADER = 0;
    int VIEW_HOLDER_ITEM = 1;
}
