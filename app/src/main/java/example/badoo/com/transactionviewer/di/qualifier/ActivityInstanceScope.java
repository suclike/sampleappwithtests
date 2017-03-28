package example.badoo.com.transactionviewer.di.qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Scope;

@Scope
@Retention(RUNTIME)
public @interface ActivityInstanceScope { }
