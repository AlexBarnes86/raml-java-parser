/*
 * Copyright 2013 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.raml.parser.annotation.Mapping;
import org.raml.parser.annotation.Value;
import org.raml.parser.resolver.MatchAllHandler;

public class SecurityReference
{


    private String name;

    @Mapping(handler = MatchAllHandler.class)
    private Map<String, List<String>> parameters = new HashMap<String, List<String>>();


    public SecurityReference(@Value String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Map<String, List<String>> getParameters()
    {
        return parameters;
    }

    public void setParameters(Map<String, List<String>> parameters)
    {
        this.parameters = parameters;
    }
}
