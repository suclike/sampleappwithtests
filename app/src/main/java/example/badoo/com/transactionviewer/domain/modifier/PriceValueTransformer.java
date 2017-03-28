package example.badoo.com.transactionviewer.domain.modifier;

import javax.inject.Inject;

import android.support.annotation.NonNull;

public class PriceValueTransformer implements DataTransformer<Double, Double, Double> {

    @Inject
    public PriceValueTransformer() { }

    @NonNull
    @Override
    public Double transform(@NonNull final Double current, @NonNull final Double multiplier) {
        return current * multiplier;
    }
}
