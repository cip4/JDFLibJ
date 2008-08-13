/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.ifaces;

import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;

/**
 * common Interface for ContentObjects and MarkObjects
 * 
 * @author prosirai
 * 
 */
public interface IPlacedObject
{

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SourceClipPath
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SourceClipPath
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setSourceClipPath(String value);

	/**
	 * (23) get String attribute SourceClipPath
	 * 
	 * @return the value of the attribute
	 */
	public abstract String getSourceClipPath();

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LayerID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LayerID
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setLayerID(int value);

	/**
	 * (15) get int attribute LayerID
	 * 
	 * @return int the value of the attribute
	 */
	public abstract int getLayerID();

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CTM
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CTM
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setCTM(JDFMatrix value);

	/**
	 * (20) get JDFMatrix attribute CTM
	 * 
	 * @return JDFMatrixthe value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public abstract JDFMatrix getCTM();

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Ord
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Ord
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setOrd(int value);

	/**
	 * (15) get int attribute Ord
	 * 
	 * @return int the value of the attribute
	 */
	public abstract int getOrd();

	/*
	 * --------------------------------------------------------------------- Methods for Attribute HalfTonePhaseOrigin
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute HalfTonePhaseOrigin
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setHalfTonePhaseOrigin(JDFXYPair value);

	/**
	 * (20) get JDFXYPair attribute HalfTonePhaseOrigin
	 * 
	 * @return JDFXYPairthe value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public abstract JDFXYPair getHalfTonePhaseOrigin();

	/*
	 * --------------------------------------------------------------------- Methods for Attribute OrdID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute OrdID
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setOrdID(int value);

	/**
	 * (15) get int attribute OrdID
	 * 
	 * @return int the value of the attribute
	 */
	public abstract int getOrdID();

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TrimCTM
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TrimCTM
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setTrimCTM(JDFMatrix value);

	/**
	 * convenience method to set TrimSize
	 * 
	 * @param xy : the value to set TrimSize to
	 */
	public abstract void setTrimSize(JDFXYPair xy);

	/**
	 * convenience method to set TrimSize
	 * 
	 * @param x : the value to set the x Dimension to
	 * @param x : the value to set the y Dimension to
	 */
	public abstract void setTrimSize(double x, double y);

	/**
	 * (20) get JDFMatrix attribute TrimCTM
	 * 
	 * @return JDFMatrixthe value of the attribute, null if a the attribute value is not a valid to create a JDFMatrix
	 */
	public abstract JDFMatrix getTrimCTM();

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ClipBox
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipBox
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setClipBox(JDFRectangle value);

	/**
	 * (20) get JDFRectangle attribute ClipBox
	 * 
	 * @return JDFRectanglethe value of the attribute, null if a the attribute value is not a valid to create a
	 *         JDFRectangle
	 */
	public abstract JDFRectangle getClipBox();

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ClipPath
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ClipPath
	 * 
	 * @param value : the value to set the attribute to
	 */
	public abstract void setClipPath(String value);

	/**
	 * (23) get String attribute ClipPath
	 * 
	 * @return the value of the attribute
	 */
	public abstract String getClipPath();

}
