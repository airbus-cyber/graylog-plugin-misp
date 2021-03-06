/*
 * Copyright (C) 2018 Airbus CyberSecurity (SAS)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */
package com.airbus_cyber_security.graylog.pipelineprocessor.functions;

import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.TypeLiteral;
import org.graylog2.plugin.PluginModule;
import org.graylog.plugins.pipelineprocessor.ast.functions.Function;

public class MispModule extends PluginModule {

    @Override
    protected void configure() {
        addMessageProcessorFunction(MispLookupFunction.NAME, MispLookupFunction.class);
    }

    private void addMessageProcessorFunction(String name, Class<? extends Function<?>> functionClass) {
        MapBinder<String, Function<?>> processorFunctionBinder = createProcessorFunctionBinder();
        processorFunctionBinder.addBinding(name).to(functionClass).asEagerSingleton();
    }

    private MapBinder<String, Function<?>> createProcessorFunctionBinder() {
        Binder binder = binder();
        TypeLiteral<String> keyType = TypeLiteral.get(String.class);
        TypeLiteral<Function<?>> valueType = new TypeLiteral<Function<?>>() {};
        return MapBinder.newMapBinder(binder, keyType, valueType);
    }
}
