package example.badoo.com.transactionviewer.domain.modifier;

import javax.inject.Inject;

import android.support.annotation.NonNull;

public class PriceValueConverter implements DataConverter<String, Float> {

    @Inject
    public PriceValueConverter() { }

    @NonNull
    @Override
    public Float convert(@NonNull final String from) {
        return Float.parseFloat(from);
    }
}
