package example.badoo.com.transactionviewer.domain.modifier;

import android.support.annotation.NonNull;

interface DataConverter<From, To> {

    @NonNull
    To convert(@NonNull From from);
}
