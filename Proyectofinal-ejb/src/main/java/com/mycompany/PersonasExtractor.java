/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany;

import org.eclipse.persistence.descriptors.ClassExtractor;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;


public class PersonasExtractor extends ClassExtractor {

    @Override
    public Class extractClassFromRow(Record record, Session sn) {
        if(record.containsKey("Afiliado")){
            return Afiliados.class;
        }else{
            return Personas.class;
        }
    }
    
}
