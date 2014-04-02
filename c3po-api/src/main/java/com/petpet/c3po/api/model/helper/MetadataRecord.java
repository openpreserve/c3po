/*******************************************************************************
 * Copyright 2013 Petar Petrov <me@petarpetrov.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.petpet.c3po.api.model.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.petpet.c3po.api.model.Property;

/**
 * A single metadata record of an element.
 *
 * @author Petar Petrov <me@petarpetrov.org>
 *
 */
public class MetadataRecord {

    /**
     * The status of the element shows the certainty with which the value for the
     * given property is correct.
     *
     * @author Petar Petrov <me@petarpetrov.org>
     *
     */
    public static enum Status {
        /**
         * Means that more than one tools confirm the value.
         */
        OK,

        /**
         * Only one tool has given this value.
         */
        SINGLE_RESULT,

        /**
         * One, two or more tools have provided different values for the same
         * property.
         */
        CONFLICT
    }

    public static enum Type {
        IDENTIFICATION,
        FEATURE
    }

    /**
     * The property to which the value of this record belongs.
     */
    private Property property;

    /**
     * The actual measured value.
     */
    private String value;

    /**
     * A list of sources that have measured the value.
     */
    private List<String> sources;

    /**
     * A list for the conflicting values;
     */
    //private List<String> values;

    private HashMap<String, List<String>>  values;

    /**
     * The status of the value.
     *
     * @see Status
     */
    private String status;
    private String type;

    private List<MetadataRecord> identity;


    /**
     * Creates an empty record with a status ok.
     */
    public MetadataRecord() {
        this.sources = new ArrayList<String>();
        this.status = Status.OK.name();
        this.type=Type.FEATURE.name();
    }

    /**
     * Creates an record for the given property with the given value and a status
     * SINGLE_RESULT.
     *
     * @param p
     * @param value
     */
    public MetadataRecord(Property p, String value) {
        this();
        this.property = p;
        this.value = value;
        this.status = Status.SINGLE_RESULT.name();
    }

    /**
     * Creates a record for the given property with the given value and the given
     * status.
     *
     * @param p
     * @param value
     * @param status
     */
    public MetadataRecord(Property p, String value, Status status) {
        this( p, value );
        this.status = status.name();
    }
    public MetadataRecord(Property p, String value, String status) {
        this( p, value );
        this.status = status;
    }
    public MetadataRecord(Property p, String value, String status, List<String> sources) {
        this( p, value );
        this.status = status;
        this.sources=sources;
    }


    public MetadataRecord(Property p, HashMap<String, List<String>>  values, Status status) {
        this();
        this.property = p;
        this.values = values;
        this.status = status.name();
    }
    public MetadataRecord(Property p, List<MetadataRecord> identity, String status){
        this.property = p;
        this.identity=identity;
        this.status=status;
        this.type=Type.IDENTIFICATION.name();
    }

    public Property getProperty() {
        return this.property;
    }

    public void setProperty( Property p ) {
        this.property = p;
    }

    public String getValue() {
        return value;
    }

    public void setValue( String value ) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources( List<String> sources ) {
        this.sources = sources;
    }

    public HashMap<String, List<String>> getValues() {
        if ( values == null ) {
            this.values = new HashMap<String, List<String>>();
        }
        return values;
    }

    public void setValues( HashMap<String, List<String>>  values ) {
        this.values = values;
    }

    public void setIdentity(List<MetadataRecord> identity){
        this.identity=identity;
    }
    public List<MetadataRecord> getIdentity(){
        return this.identity;
    }


    @Override
    public String toString() {
        return "MetadataRecord [property=" + property.getId() + ", value=" + value
                + ", status=" + status + ", sources=" + sources + "]";
    }
}
