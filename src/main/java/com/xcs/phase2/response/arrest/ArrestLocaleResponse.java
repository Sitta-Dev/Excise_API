package com.xcs.phase2.response.arrest;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ArrestLocaleResponse extends ArrestResponse{

	private int LOCALE_ID;
}
