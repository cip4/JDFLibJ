/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.junit.jupiter.api.Test;

class WalkSetTest extends WalkSet
{

	@Test
	void testReorder()
	{
		final WalkSet set = new WalkSet();
		set.setParent(new XJDFToJDFImpl(null));
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 4; i++)
		{
			final JDFAttributeMap map = new JDFAttributeMap("Run", "Run" + i);
			for (int k = 0; k < 2; k++)
			{
				map.put("RunPage", "" + k);
				v.add(map.clone());
			}
		}
		final XJDFHelper h = new XJDFHelper("j1", null, v);
		set.reorderResources(h.getNodeInfo().getRoot());
		assertEquals(8, h.getNodeInfo().getPartitionList().size());
	}

	@Test
	void testReorder3()
	{
		final WalkSet set = new WalkSet();
		set.setParent(new XJDFToJDFImpl(null));
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 3; i++)
		{
			final JDFAttributeMap map = new JDFAttributeMap("Run", "Run" + i);
			for (int j = 0; j < 2; j++)
			{
				map.put("Option", "" + j);
				v.add(map.clone());
			}
		}
		final XJDFHelper h = new XJDFHelper("j1", null, v);
		set.reorderResources(h.getNodeInfo().getRoot());
		final List<ResourceHelper> l = h.getNodeInfo().getPartitionList();
		assertEquals(6, l.size());
	}

	@Test
	void testReordersingle()
	{
		final WalkSet set = new WalkSet();
		set.setParent(new XJDFToJDFImpl(null));
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 3; i++)
		{
			final JDFAttributeMap map = new JDFAttributeMap("Run", "Run" + i);
			for (int j = 0; j < 2; j++)
			{
				map.put("Option", "" + j);
				for (int k = 0; k < j + 1; k++)
				{
					map.put("RunPage", "" + k);
					v.add(map.clone());
				}
			}
		}
		final XJDFHelper h = new XJDFHelper("j1", null, v);
		set.reorderResources(h.getNodeInfo().getRoot());
		final List<ResourceHelper> l = h.getNodeInfo().getPartitionList();
		assertEquals(9, l.size());
	}

	@Test
	void testAddLower()
	{
		final WalkSet set = new WalkSet();
		set.setParent(new XJDFToJDFImpl(null));
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 4; i++)
		{
			final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.BlockName, "Run" + i);
			for (int k = 0; k < 2; k++)
			{
				map.put(EnumPartIDKey.CellIndex, "" + k);
				v.add(map.clone());
			}
		}
		final XJDFHelper h = new XJDFHelper("j1", null, v);
		final List<ResourceHelper> l = set.addLowerParts(h.getNodeInfo().getRoot());
		assertEquals(12, l.size());
	}

	@Test
	void testAddLowerSSS()
	{
		final WalkSet set = new WalkSet();
		set.setParent(new XJDFToJDFImpl(null));
		final VJDFAttributeMap v = new VJDFAttributeMap();
		for (int i = 0; i < 2; i++)
		{
			final JDFAttributeMap map = new JDFAttributeMap("SheetName", "S" + i);
			for (int j = 0; j < 2; j++)
			{
				map.put("Separation", "sep" + j);
				for (int k = 0; k < 2; k++)
				{
					map.put("Side", (EnumSide) EnumSide.getEnumList().get(k));
					v.add(map.clone());
				}
			}
		}
		final XJDFHelper h = new XJDFHelper("j1", null, v);
		final List<ResourceHelper> l = set.addLowerParts(h.getNodeInfo().getRoot());
		assertEquals(8, l.size());
	}

}
