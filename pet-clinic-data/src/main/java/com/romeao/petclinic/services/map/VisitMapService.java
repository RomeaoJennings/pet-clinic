package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Visit;
import com.romeao.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit> implements VisitService { }
