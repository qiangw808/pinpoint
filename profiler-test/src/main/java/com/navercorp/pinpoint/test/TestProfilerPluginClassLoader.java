/*
 * Copyright 2014 NAVER Corp.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.navercorp.pinpoint.test;

import com.navercorp.pinpoint.exception.PinpointException;
import com.navercorp.pinpoint.profiler.instrument.ClassInjector;

import java.io.InputStream;

/**
 * @author Jongho Moon
 * @author jaehong.kim
 */
public class TestProfilerPluginClassLoader implements ClassInjector {

    @SuppressWarnings("unchecked")
    @Override
    public <T> Class<? extends T> injectClass(ClassLoader targetClassLoader, String className) {
        try {
            return (Class<? extends T>) targetClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new PinpointException("Cannot find class: " + className, e);
        }
    }

    @Override
    public InputStream getResourceAsStream(ClassLoader targetClassLoader, String classPath) {
        ClassLoader classLoader = targetClassLoader;
        if(classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }

        return classLoader.getResourceAsStream(classPath);
    }
}
