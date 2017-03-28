package example.badoo.com.transactionviewer.domain.modifier;

import android.support.annotation.NonNull;

interface DataTransformer<From, With, To> {

    @NonNull
    To transform(@NonNull From from, @NonNull With with);
}
