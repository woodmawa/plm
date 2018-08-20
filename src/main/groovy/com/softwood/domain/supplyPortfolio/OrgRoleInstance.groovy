package com.softwood.domain.supplyPortfolio

import com.softwood.utilities.SequenceGenerator
import groovy.transform.MapConstructor


@MapConstructor (post = {id = SequenceGenerator.standard.next() })
class OrgRoleInstance {
    long id
    String orgName
    OrgRoleType role = OrgRoleType.SUPPLIER  //default role
}

enum OrgRoleType {
    SUPPLIER, MAINTAINER, MANUFACTURER, PROVIDER, SERVICE_PROVIDER
}
