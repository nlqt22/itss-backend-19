package com.pinkieyun.fitnesscenter.service.criteria;

import com.pinkieyun.fitnesscenter.filter.DateFilter;
import com.pinkieyun.fitnesscenter.filter.IntegerFilter;
import com.pinkieyun.fitnesscenter.filter.StringFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCriteria {

    private IntegerFilter id;

    private StringFilter fullName;

    private DateFilter dob;

    private StringFilter phone;
    
    private StringFilter identityCard;

}
