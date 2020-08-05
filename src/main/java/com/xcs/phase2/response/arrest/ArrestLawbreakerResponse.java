package com.xcs.phase2.response.arrest;

import lombok.Data;

@Data
public class ArrestLawbreakerResponse extends ArrestResponse{

	private int LAWBREAKER_ID;
	private int PERSON_ID;
}
