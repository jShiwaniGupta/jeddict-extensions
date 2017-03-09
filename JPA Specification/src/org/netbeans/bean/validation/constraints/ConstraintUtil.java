/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.bean.validation.constraints;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.netbeans.jcode.core.util.AttributeType.BIGDECIMAL;
import static org.netbeans.jcode.core.util.AttributeType.BIGINTEGER;
import static org.netbeans.jcode.core.util.AttributeType.BOOLEAN;
import static org.netbeans.jcode.core.util.AttributeType.BOOLEAN_WRAPPER;
import static org.netbeans.jcode.core.util.AttributeType.BYTE;
import static org.netbeans.jcode.core.util.AttributeType.BYTE_ARRAY;
import static org.netbeans.jcode.core.util.AttributeType.BYTE_WRAPPER;
import static org.netbeans.jcode.core.util.AttributeType.BYTE_WRAPPER_ARRAY;
import static org.netbeans.jcode.core.util.AttributeType.CALENDAR;
import static org.netbeans.jcode.core.util.AttributeType.CHAR;
import static org.netbeans.jcode.core.util.AttributeType.CHAR_ARRAY;
import static org.netbeans.jcode.core.util.AttributeType.CHAR_WRAPPER;
import static org.netbeans.jcode.core.util.AttributeType.CHAR_WRAPPER_ARRAY;
import static org.netbeans.jcode.core.util.AttributeType.DATE;
import static org.netbeans.jcode.core.util.AttributeType.DOUBLE;
import static org.netbeans.jcode.core.util.AttributeType.DOUBLE_WRAPPER;
import static org.netbeans.jcode.core.util.AttributeType.FLOAT;
import static org.netbeans.jcode.core.util.AttributeType.FLOAT_WRAPPER;
import static org.netbeans.jcode.core.util.AttributeType.INT;
import static org.netbeans.jcode.core.util.AttributeType.INT_WRAPPER;
import static org.netbeans.jcode.core.util.AttributeType.LOCAL_DATE;
import static org.netbeans.jcode.core.util.AttributeType.LONG;
import static org.netbeans.jcode.core.util.AttributeType.LONG_WRAPPER;
import static org.netbeans.jcode.core.util.AttributeType.SHORT;
import static org.netbeans.jcode.core.util.AttributeType.SHORT_WRAPPER;
import static org.netbeans.jcode.core.util.AttributeType.STRING;
import static org.netbeans.jcode.core.util.AttributeType.STRING_FQN;
import static org.netbeans.jcode.core.util.AttributeType.UUID;
import static org.netbeans.jcode.core.util.AttributeType.ZONED_DATE_TIME;

/**
 *
 * @author jGauravGupta
 */
public class ConstraintUtil {
    private static final Map<String, Function<Map<String,Constraint>,String>> DEFAULT_VALUE = new HashMap<>();
    private static final Map<String, Function<Map<String,Constraint>,String>> UPDATE_VALUE = new HashMap<>();

    private static final BiFunction<Map<String, Constraint>, String, String> TEXT_VALUE = (cm, packet) -> {
        Size size = (Size) cm.get(Size.class.getSimpleName());
        final int MAX_LIMIT = 5;
        int min, max;
        if (size != null) {
            min = size.getMin() != null ? size.getMin() : 0;
            max = size.getMax() != null ? size.getMax() : (min + MAX_LIMIT);
        } else {
            min = 0;
            max = MAX_LIMIT;
        }
        if(min < max){
            ++min;
        }
        return Stream.generate(() -> packet).limit(min).collect(Collectors.joining("", "\"", "\""));
    };

    private static final BiFunction<Map<String, Constraint>, Long, String> NUMBER_VALUE = (cm, packet) -> {
        Min minCons = (Min) cm.get(Min.class.getSimpleName());
        Max maxCons = (Max) cm.get(Max.class.getSimpleName());
        Long min = minCons != null? minCons.getValue() : null;
        Long max = maxCons != null? maxCons.getValue() : null;
        Long result;
        if (min == null && max != null) {
            result = max - packet;
        } else if (min != null && max == null) {
            result = min + packet;
        } else if (min != null && max != null) {
            result = (max - min / 2) > packet ? min + packet : min;
        } else {
            result = packet;
        }
        return String.valueOf(result);
    };
    
    static {
        Function<Map<String,Constraint>,String> number = cm -> NUMBER_VALUE.apply(cm, 1L);
        Function<Map<String,Constraint>,String> text = cm -> TEXT_VALUE.apply(cm, "A");
        
        DEFAULT_VALUE.put(BYTE, number);
        DEFAULT_VALUE.put(SHORT, number);
        DEFAULT_VALUE.put(INT, number);
        DEFAULT_VALUE.put(LONG, number.andThen(digit -> digit + "L"));
        DEFAULT_VALUE.put(FLOAT, number.andThen(digit -> digit + ".0F"));
        DEFAULT_VALUE.put(DOUBLE, number.andThen(digit -> digit + ".0D"));
        DEFAULT_VALUE.put(BOOLEAN, cm -> "false");
        DEFAULT_VALUE.put(CHAR, cm -> "'a'");

        DEFAULT_VALUE.put(BYTE_WRAPPER, number);
        DEFAULT_VALUE.put(SHORT_WRAPPER, number);
        DEFAULT_VALUE.put(INT_WRAPPER, number);
        DEFAULT_VALUE.put(LONG_WRAPPER, number.andThen(digit -> digit + "L"));
        DEFAULT_VALUE.put(FLOAT_WRAPPER, number.andThen(digit -> digit + ".0F"));
        DEFAULT_VALUE.put(DOUBLE_WRAPPER, number.andThen(digit -> digit + ".0D"));
        DEFAULT_VALUE.put(BOOLEAN_WRAPPER, cm -> "false");
        DEFAULT_VALUE.put(CHAR_WRAPPER, cm -> "'a'");

        
        DEFAULT_VALUE.put(BIGDECIMAL, number.andThen(digit -> String.format("new java.math.BigDecimal(%s.0)",digit)));
        DEFAULT_VALUE.put(BIGINTEGER, number.andThen(digit -> String.format("new java.math.BigInteger(%s)",digit)));
        DEFAULT_VALUE.put(STRING, text);
        DEFAULT_VALUE.put(STRING_FQN, text);
        
        //TODO : @Future & @Past for Date        
        DEFAULT_VALUE.put(CALENDAR, cm -> "java.util.Calendar.getInstance()");
        DEFAULT_VALUE.put(DATE, cm -> "java.util.Date.from(java.time.Instant.EPOCH)");
//        DEFAULT_VALUE.put(SQL_DATE, cm -> "java.sql.Date.from(java.time.Instant.EPOCH)");
//        DEFAULT_VALUE.put(SQL_TIME, cm -> "java.sql.Time.from(java.time.Instant.EPOCH)");
//        DEFAULT_VALUE.put(SQL_TIMESTAMP, cm -> "java.sql.Timestamp.from(java.time.Instant.EPOCH)");
        DEFAULT_VALUE.put(BYTE_ARRAY, cm -> "new byte[] {1, 2, 3}");
        DEFAULT_VALUE.put(BYTE_WRAPPER_ARRAY, cm -> "new Byte[] {1, 2, 3}");
        DEFAULT_VALUE.put(CHAR_ARRAY, cm -> "new char[] {'a', 'b', 'c'}");
        DEFAULT_VALUE.put(CHAR_WRAPPER_ARRAY, cm -> "new Character[] {'a', 'b', 'c'}");

        DEFAULT_VALUE.put(UUID, cm -> "java.util.UUID.randomUUID()");
        DEFAULT_VALUE.put(LOCAL_DATE, cm -> "java.time.LocalDate.ofEpochDay(0L)");
        DEFAULT_VALUE.put(ZONED_DATE_TIME, cm -> "java.time.ZonedDateTime.ofInstant(java.time.Instant.ofEpochMilli(0L), java.time.ZoneId.systemDefault())");
    }

    static {
                
        Function<Map<String,Constraint>,String> number = cm -> NUMBER_VALUE.apply(cm, 2L);
        Function<Map<String,Constraint>,String> text = cm -> TEXT_VALUE.apply(cm, "B");
        UPDATE_VALUE.put(BYTE, number);
        UPDATE_VALUE.put(SHORT, number);
        UPDATE_VALUE.put(INT, number);
        UPDATE_VALUE.put(LONG, number.andThen(digit -> digit + "L"));
        UPDATE_VALUE.put(FLOAT, number.andThen(digit -> digit + ".0F"));
        UPDATE_VALUE.put(DOUBLE, number.andThen(digit -> digit + ".0D"));
        UPDATE_VALUE.put(BOOLEAN, cm -> "true");
        UPDATE_VALUE.put(CHAR, cm -> "'b'");

        UPDATE_VALUE.put(BYTE_WRAPPER, number);
        UPDATE_VALUE.put(SHORT_WRAPPER, number);
        UPDATE_VALUE.put(INT_WRAPPER, number);
        UPDATE_VALUE.put(LONG_WRAPPER, number.andThen(digit -> digit + "L"));
        UPDATE_VALUE.put(FLOAT_WRAPPER, number.andThen(digit -> digit + ".0F"));
        UPDATE_VALUE.put(DOUBLE_WRAPPER, number.andThen(digit -> digit + ".0D"));
        UPDATE_VALUE.put(BOOLEAN_WRAPPER, cm -> "true");
        UPDATE_VALUE.put(CHAR_WRAPPER, cm -> "'b'");
        
       
        UPDATE_VALUE.put(BIGDECIMAL, number.andThen(digit -> String.format("new java.math.BigDecimal(%s.0)",digit)));
        UPDATE_VALUE.put(BIGINTEGER, number.andThen(digit -> String.format("new java.math.BigInteger(%s)",digit)));
        UPDATE_VALUE.put(STRING, text);
        UPDATE_VALUE.put(STRING_FQN, text);
        
        //TODO : @Future & @Past for Date        
        UPDATE_VALUE.put(CALENDAR, cm -> "java.util.Calendar.getInstance()");
        UPDATE_VALUE.put(DATE, cm -> "java.util.Date.from(java.time.Instant.now())");
//        UPDATE_VALUE.put(SQL_DATE, cm -> "java.sql.Date.from(java.time.Instant.now())");
//        UPDATE_VALUE.put(SQL_TIME, cm -> "java.sql.Time.from(java.time.Instant.now())");
//        UPDATE_VALUE.put(SQL_TIMESTAMP, cm -> "java.sql.Timestamp.from(java.time.Instant.now())");
        UPDATE_VALUE.put(BYTE_ARRAY, cm -> "new byte[] {4, 5, 6}");
        UPDATE_VALUE.put(BYTE_WRAPPER_ARRAY, cm -> "new Byte[] {4, 5, 6}");
        UPDATE_VALUE.put(CHAR_ARRAY, cm -> "new char[] {'d', 'e', 'f'}");
        UPDATE_VALUE.put(CHAR_WRAPPER_ARRAY, cm -> "new Character[] {'d', 'e', 'f'}");

        UPDATE_VALUE.put(UUID, cm -> "java.util.UUID.randomUUID()");
        UPDATE_VALUE.put(LOCAL_DATE, cm -> "java.time.LocalDate.now(java.time.ZoneId.systemDefault())");
        UPDATE_VALUE.put(ZONED_DATE_TIME, cm -> "java.time.ZonedDateTime.now(java.time.ZoneId.systemDefault()).withNano(0)");

    }

    public static String getAttributeDefaultValue(String type) {
        return getAttributeDefaultValue(type, Collections.EMPTY_MAP);
    }
    
    public static String getAttributeDefaultValue(String type, Map<String,Constraint> constraints) {
        Function<Map<String,Constraint>,String> eval = DEFAULT_VALUE.get(type);
        return eval != null? eval.apply(constraints): null;
    }
    
    public static String getAttributeUpdateValue(String type) {
        return getAttributeUpdateValue(type, Collections.EMPTY_MAP);
    }
    
    public static String getAttributeUpdateValue(String type, Map<String,Constraint> constraints) {
        Function<Map<String,Constraint>,String> eval = UPDATE_VALUE.get(type);
        return eval != null? eval.apply(constraints): null;
    }
}