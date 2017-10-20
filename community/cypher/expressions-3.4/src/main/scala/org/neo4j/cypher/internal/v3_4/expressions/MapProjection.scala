/*
 * Copyright (c) 2002-2017 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
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
package org.neo4j.cypher.internal.v3_4.expressions

import org.neo4j.cypher.internal.util.v3_4.InputPosition

case class MapProjection(
                          name: Variable,
                          items: Seq[MapProjectionElement],
                          definitionPos: Option[InputPosition] = None)
                        (val position: InputPosition)
  extends Expression {

  def withDefinitionPos(pos:InputPosition): MapProjection =
    copy(definitionPos = Some(pos))(position)
}

sealed trait MapProjectionElement extends Expression

case class LiteralEntry(key: PropertyKeyName, exp: Expression)(val position: InputPosition) extends MapProjectionElement
case class VariableSelector(id: Variable)(val position: InputPosition) extends MapProjectionElement
case class PropertySelector(id: Variable)(val position: InputPosition) extends MapProjectionElement
case class AllPropertiesSelector()(val position: InputPosition) extends MapProjectionElement