package com.example.demo.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BMI {
	private Double height; 
	private Double weight; 
	private Double bmi;
}
