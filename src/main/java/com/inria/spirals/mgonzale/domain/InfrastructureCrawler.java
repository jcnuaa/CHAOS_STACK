package com.inria.spirals.mgonzale.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.inria.spirals.mgonzale.services.OpenStackConnection;

abstract class InfrastructureCrawler implements Infrastructure {

	@Autowired
	private OpenStackConnection os;

    @Override
    public final Set<Member> getMembers() {
        Set<Member> members = new HashSet<>();
        
        os.findAllServers().forEach(
        		
        		virtualMachine -> {
                    String id = virtualMachine.getId();
                    String job = virtualMachine.getHypervisorHostname();
                    String name = virtualMachine.getInstanceName();
                    
                    members.add(new Member(id, virtualMachine.getAvailabilityZone(), job, name));
                }
        		
        		
        		
        		);

        return members;
    }

 
}

