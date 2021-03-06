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
package org.raml.parser.resolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.raml.parser.completion.KeySuggestion;
import org.raml.parser.completion.Suggestion;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;

public class EnumHandler implements TupleHandler
{


    private Class<? extends Enum> enumClass;

    public EnumHandler(Class<? extends Node> tupleValueType, Class<? extends Enum> enumClass)
    {
        this.enumClass = enumClass;
    }

    @Override
    public boolean handles(NodeTuple tuple)
    {
        if (tuple.getKeyNode() instanceof ScalarNode)
        {
            String enumValue = ((ScalarNode) tuple.getKeyNode()).getValue();
            try
            {
                Enum.valueOf(enumClass, enumValue.toUpperCase());
            }
            catch (IllegalArgumentException e)
            {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Suggestion> getSuggestions()
    {
        List<Suggestion> result = new ArrayList<Suggestion>();
        Field[] declaredFields = enumClass.getDeclaredFields();
        for (Field declaredField : declaredFields)
        {
            if (declaredField.isEnumConstant())
            {
                result.add(new KeySuggestion(declaredField.getName().toLowerCase()));
            }
        }

        return result;
    }
}
