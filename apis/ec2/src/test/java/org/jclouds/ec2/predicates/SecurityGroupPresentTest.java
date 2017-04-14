/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.ec2.predicates;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimaps;
import org.jclouds.compute.internal.BaseComputeServiceContextLiveTest;
import org.jclouds.ec2.EC2Api;
import org.jclouds.ec2.compute.domain.RegionAndName;
import org.jclouds.ec2.compute.predicates.SecurityGroupPresent;
import org.jclouds.ec2.domain.SecurityGroup;
import org.jclouds.ec2.features.AvailabilityZoneAndRegionApi;
import org.jclouds.ec2.features.SecurityGroupApi;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.Set;

/**
 * Tests provisioning of instances to non-default VPCs.
 */
@Test(groups = "live", singleThreaded = true)
public class SecurityGroupPresentTest extends BaseComputeServiceContextLiveTest {

    private static final String IDENTITY = "...";
    private static final String CREDENTIAL = "...";
    private static final String REGION = "us-west-2";

    private static final String DEFAULT_VPC = "vpc-34d8c250";
    private static final String OTHER_VPC = "vpc-968144f1";

    private static final String DEFAULT_VPC_SG = "default_elb_d308e073-cbc7-372a-ae66-605891064575";
    private static final String OTHER_VPC_SG = "jclouds#brooklyn-oo1msn-sam-two-servers-diff-is09-server-fgh3";

    public SecurityGroupPresentTest() {
        provider = "ec2";
    }

    private EC2Api ec2Api;
    private AvailabilityZoneAndRegionApi client;

    @Override
    @BeforeClass(groups = { "integration", "live" })
    public void setupContext() {
        super.setupContext();
        ec2Api = view.unwrapApi(EC2Api.class);
        client = ec2Api.getAvailabilityZoneAndRegionApi().get();
    }

    @Override
    protected Properties setupProperties() {
        Properties props = super.setupProperties();
        if (props.getProperty(provider + ".identity") == null) {
            props.setProperty(provider + ".identity", IDENTITY);
        }
        if (props.getProperty(provider + ".credential") == null) {
            props.setProperty(provider + ".credential", CREDENTIAL);
        }
        if (props.getProperty(provider + ".endpoint") == null) {
            props.setProperty(provider + ".endpoint", "https://ec2." + REGION + ".amazonaws.com");
        }
        return props;
    }


//    @Test
//    public void testDescribeSecurityGroups() {
//        Set<SecurityGroup> securityGroups = ec2Api.getSecurityGroupApi().get()
//                .describeSecurityGroupsInRegionById(REGION, DEFAULT_VPC_SG, OTHER_VPC_SG);
//        Assert.assertEquals(securityGroups.size(), 2, securityGroups.toString());
//    }
//
    @Test
    public void testDescribeSingleSecurityGroup() {
        Set<SecurityGroup> securityGroups = ec2Api.getSecurityGroupApi().get()
                .describeSecurityGroupsInRegionWithFilter(REGION, ImmutableMultimap.of("group-name", "jclouds#sam-test"));
        Assert.assertEquals(securityGroups.size(), 1, securityGroups.toString());
    }

    @Test
    public void testSecurityGroupPresent() {
        SecurityGroupPresent securityGroupPresent = new SecurityGroupPresent(ec2Api);
//        final boolean defaultSgPresent = securityGroupPresent.apply(new RegionAndName(REGION, DEFAULT_VPC_SG));
        final boolean otherSgPresent = securityGroupPresent.apply(new RegionAndName(REGION, OTHER_VPC_SG));
        System.out.println("Checking presence of security groups...");
//        System.out.println("sg on default vpc: " + defaultSgPresent);
        System.out.println("sg on another vpc: " + otherSgPresent);
    }

    @Test
    public void testDescribeSecurityGroupsInRegion() {
        final SecurityGroupApi sga = ec2Api.getSecurityGroupApi().get();
        Set<SecurityGroup> gs = sga.describeSecurityGroupsInRegion(REGION);
        System.out.println("describeSecurityGroupsInRegion(" + REGION + ", " + OTHER_VPC_SG + ")");
        for (SecurityGroup g : gs) {
            System.out.println(" * " + g);
        }
    }

}
