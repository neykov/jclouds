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
package org.jclouds.softlayer.compute.options;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.List;
import java.util.Map;

import org.jclouds.compute.options.TemplateOptions;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.net.InternetDomainName;

/**
 * Contains options supported by the
 * {@link org.jclouds.compute.ComputeService#createNodesInGroup(String, int, TemplateOptions)} and
 * {@link org.jclouds.compute.ComputeService#createNodesInGroup(String, int, TemplateOptions)}
 * operations on the <em>gogrid</em> provider.
 * 
 * <h2>Usage</h2> The recommended way to instantiate a
 * {@link SoftLayerTemplateOptions} object is to statically import
 * {@code SoftLayerTemplateOptions.*} and invoke a static creation method
 * followed by an instance mutator (if needed):
 * <p>
 * 
 * <pre>
 * import static org.jclouds.compute.options.SoftLayerTemplateOptions.Builder.*;
 * ComputeService client = // get connection
 * templateBuilder.options(inboundPorts(22, 80, 8080, 443));
 * Set&lt;? extends NodeMetadata&gt; set = client.createNodesInGroup(tag, 2, templateBuilder.build());
 * </pre>
 * 
 */
public class SoftLayerTemplateOptions extends TemplateOptions implements Cloneable {

   protected String domainName = "jclouds.org";
   protected Optional<List<Integer>> blockDevices = Optional.absent();
   protected Optional<String> diskType = Optional.absent();
   protected Optional<Integer> portSpeed = Optional.absent();
   protected Optional<String> userData = Optional.absent();
   protected Optional<Integer> primaryNetworkComponentNetworkVlanId = Optional.absent();
   protected Optional<Integer> primaryBackendNetworkComponentNetworkVlanId = Optional.absent();
   protected Optional<Boolean> hourlyBillingFlag = Optional.absent();
   protected Optional<Boolean> dedicatedAccountHostOnlyFlag = Optional.absent();
   protected Optional<Boolean> privateNetworkOnlyFlag = Optional.absent();
   protected Optional<String> postInstallScriptUri = Optional.absent();
   protected Optional<List<Integer>> sshKeys = Optional.absent();
   protected Optional<String> notes = Optional.absent();

   @Override
   public SoftLayerTemplateOptions clone() {
      SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
      copyTo(options);
      return options;
   }

   @Override
   public void copyTo(TemplateOptions to) {
      super.copyTo(to);
      if (to instanceof SoftLayerTemplateOptions) {
         SoftLayerTemplateOptions eTo = SoftLayerTemplateOptions.class.cast(to);
         eTo.domainName(domainName);
         if (blockDevices.isPresent()) {
            eTo.blockDevices(blockDevices.get());
         }
         if (diskType.isPresent()) {
            eTo.diskType(diskType.get());
         }
         if (portSpeed.isPresent()) {
            eTo.portSpeed(portSpeed.get());
         }
         if (userData.isPresent()) {
            eTo.userData(userData.get());
         }
         if (primaryNetworkComponentNetworkVlanId.isPresent()) {
            eTo.primaryNetworkComponentNetworkVlanId(primaryNetworkComponentNetworkVlanId.get());
         }
         if (primaryBackendNetworkComponentNetworkVlanId.isPresent()) {
            eTo.primaryBackendNetworkComponentNetworkVlanId(primaryBackendNetworkComponentNetworkVlanId.get());
         }
         if (hourlyBillingFlag.isPresent()) {
            eTo.hourlyBillingFlag(hourlyBillingFlag.get());
         }
         if (dedicatedAccountHostOnlyFlag.isPresent()) {
            eTo.dedicatedAccountHostOnlyFlag(dedicatedAccountHostOnlyFlag.get());
         }
         if (privateNetworkOnlyFlag.isPresent()) {
            eTo.privateNetworkOnlyFlag(privateNetworkOnlyFlag.get());
         }
         if (sshKeys.isPresent()) {
            eTo.sshKeys(sshKeys.get());
         }
         if (notes.isPresent()) {
            eTo.notes(notes.get());
         }
      }
   }

   /**
    * will replace the default domain used when ordering virtual guests. Note
    * this needs to contain a public suffix!
    * 
    * @see org.jclouds.softlayer.features.VirtualGuestApi#createVirtualGuest(org.jclouds.softlayer.domain.VirtualGuest)
    * @see InternetDomainName#hasPublicSuffix
    */
   public SoftLayerTemplateOptions domainName(String domainName) {
      checkNotNull(domainName, "domainName was null");
      checkArgument(InternetDomainName.from(domainName).hasPublicSuffix(), "domainName %s has no public suffix",
            domainName);
      this.domainName = domainName;
      return this;
   }

   public SoftLayerTemplateOptions blockDevices(Iterable<Integer> capacities) {
      for (Integer capacity : checkNotNull(capacities, "capacities"))
         checkNotNull(capacity, "all block devices must be non-empty");
      this.blockDevices = Optional.<List<Integer>> of(ImmutableList.copyOf(capacities));
      return this;
   }

   public SoftLayerTemplateOptions blockDevices(Integer... capacities) {
      return blockDevices(ImmutableList.copyOf(checkNotNull(capacities, "capacities")));
   }

   public SoftLayerTemplateOptions diskType(String diskType) {
      checkNotNull(diskType, "diskType was null");
      this.diskType = Optional.of(diskType);
      return this;
   }

   public SoftLayerTemplateOptions portSpeed(Integer portSpeed) {
      checkNotNull(portSpeed, "portSpeed was null");
      this.portSpeed = Optional.of(portSpeed);
      return this;
   }

   public SoftLayerTemplateOptions userData(String userData) {
      checkNotNull(userData, "userData was null");
      this.userData = Optional.of(userData);
      return this;
   }

   public SoftLayerTemplateOptions primaryNetworkComponentNetworkVlanId(Integer primaryNetworkComponentNetworkVlanId) {
      checkNotNull(primaryNetworkComponentNetworkVlanId, "primaryNetworkComponentNetworkVlanId was null");
      this.primaryNetworkComponentNetworkVlanId = Optional.of(primaryNetworkComponentNetworkVlanId);
      return this;
   }

   public SoftLayerTemplateOptions primaryBackendNetworkComponentNetworkVlanId(Integer primaryBackendNetworkComponentNetworkVlanId) {
      checkNotNull(primaryBackendNetworkComponentNetworkVlanId, "primaryBackendNetworkComponentNetworkVlanId was null");
      this.primaryBackendNetworkComponentNetworkVlanId = Optional.of(primaryBackendNetworkComponentNetworkVlanId);
      return this;
   }

   public SoftLayerTemplateOptions hourlyBillingFlag(boolean hourlyBillingFlag) {
      this.hourlyBillingFlag = Optional.of(hourlyBillingFlag);
      return this;
   }

   public SoftLayerTemplateOptions dedicatedAccountHostOnlyFlag(boolean dedicatedAccountHostOnlyFlag) {
      this.dedicatedAccountHostOnlyFlag = Optional.of(dedicatedAccountHostOnlyFlag);
      return this;
   }

   public SoftLayerTemplateOptions privateNetworkOnlyFlag(boolean privateNetworkOnlyFlag) {
      this.privateNetworkOnlyFlag = Optional.of(privateNetworkOnlyFlag);
      return this;
   }

   public SoftLayerTemplateOptions postInstallScriptUri(String postInstallScriptUri) {
      checkNotNull(postInstallScriptUri, "postInstallScriptUri was null");
      this.postInstallScriptUri = Optional.of(postInstallScriptUri);
      return this;
   }

   public SoftLayerTemplateOptions sshKeys(Iterable<Integer> sshKeys) {
      for (Integer sshKey : checkNotNull(sshKeys, "sshKeys"))
         checkNotNull(sshKey, "sshKeys must be non-empty");
      this.sshKeys = Optional.<List<Integer>> of(ImmutableList.copyOf(sshKeys));
      return this;
   }

   public SoftLayerTemplateOptions sshKeys(Integer... sshKeys) {
      return sshKeys(ImmutableList.copyOf(checkNotNull(sshKeys, "sshKeys")));
   }

   public SoftLayerTemplateOptions notes(String notes) {
      this.notes = Optional.of(notes);
      return this;
   }

   public String getDomainName() {
      return domainName;
   }

   public Optional<List<Integer>> getBlockDevices() {
      return blockDevices;
   }

   public Optional<String> getDiskType() {
      return diskType;
   }

   public Optional<Integer> getPortSpeed() {
      return portSpeed;
   }

   public Optional<String> getUserData() { return userData; }

   public Optional<Integer> getPrimaryNetworkComponentNetworkVlanId() { return primaryNetworkComponentNetworkVlanId; }

   public Optional<Integer> getPrimaryBackendNetworkComponentNetworkVlanId() { return primaryBackendNetworkComponentNetworkVlanId; }

   public Optional<Boolean> isHourlyBillingFlag() { return hourlyBillingFlag; }

   public Optional<Boolean> isDedicatedAccountHostOnlyFlag() { return dedicatedAccountHostOnlyFlag; }

   public Optional<Boolean> isPrivateNetworkOnlyFlag() { return privateNetworkOnlyFlag; }

   public Optional<String> getPostInstallScriptUri() { return postInstallScriptUri; }

   public Optional<List<Integer>> getSshKeys() {
      return sshKeys;
   }

   public Optional<String> getNotes() {
      return notes;
   }

   public static final SoftLayerTemplateOptions NONE = new SoftLayerTemplateOptions();

   public static class Builder {

      /**
       * @see #domainName
       */
      public static SoftLayerTemplateOptions domainName(String domainName) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.domainName(domainName));
      }

      /**
       * @see #blockDevices
       */
      public static SoftLayerTemplateOptions blockDevices(Integer... capacities) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.blockDevices(capacities));
      }

      public static SoftLayerTemplateOptions blockDevices(Iterable<Integer> capacities) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.blockDevices(capacities));
      }

      /**
       * @see #diskType
       */
      public static SoftLayerTemplateOptions diskType(String diskType) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.diskType(diskType));
      }

      /**
       * @see #portSpeed
       */
      public static SoftLayerTemplateOptions portSpeed(Integer portSpeed) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.portSpeed(portSpeed));
      }

      /**
       * @see #userData
       */
      public static SoftLayerTemplateOptions userData(String userData) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.userData(userData));
      }

      /**
       * @see #primaryNetworkComponentNetworkVlanId
       */
      public static SoftLayerTemplateOptions primaryNetworkComponentNetworkVlanId(Integer primaryNetworkComponentNetworkVlanId) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.primaryNetworkComponentNetworkVlanId(primaryNetworkComponentNetworkVlanId));
      }

      /**
       * @see #primaryBackendNetworkComponentNetworkVlanId
       */
      public static SoftLayerTemplateOptions primaryBackendNetworkComponentNetworkVlanId(Integer primaryBackendNetworkComponentNetworkVlanId) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.primaryBackendNetworkComponentNetworkVlanId(primaryBackendNetworkComponentNetworkVlanId));
      }

      /**
       * @see #hourlyBillingFlag
       */
      public static SoftLayerTemplateOptions hourlyBillingFlag(boolean hourlyBillingFlag) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.hourlyBillingFlag(hourlyBillingFlag));
      }

      /**
       * @see #dedicatedAccountHostOnlyFlag
       */
      public static SoftLayerTemplateOptions dedicatedAccountHostOnlyFlag(boolean dedicatedAccountHostOnlyFlag) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.dedicatedAccountHostOnlyFlag(dedicatedAccountHostOnlyFlag));
      }

      /**
       * @see #privateNetworkOnlyFlag
       */
      public static SoftLayerTemplateOptions privateNetworkOnlyFlag(boolean privateNetworkOnlyFlag) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.privateNetworkOnlyFlag(privateNetworkOnlyFlag));
      }

      /**
       * @see #postInstallScriptUri(String)
       */
      public static SoftLayerTemplateOptions postInstallScriptUri(String postInstallScriptUri) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.postInstallScriptUri(postInstallScriptUri));
      }

      /**
       * @see #sshKeys(Iterable)
       */
      public static SoftLayerTemplateOptions sshKeys(Integer... sshKeys) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.sshKeys(sshKeys));
      }

      public static SoftLayerTemplateOptions sshKeys(Iterable<Integer> sshKeys) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.blockDevices(sshKeys));
      }

      public static SoftLayerTemplateOptions notes(String notes) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.notes(notes));
      }

      // methods that only facilitate returning the correct object type

      /**
       * @see TemplateOptions#inboundPorts(int...)
       */
      public static SoftLayerTemplateOptions inboundPorts(int... ports) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.inboundPorts(ports));
      }

      /**
       * @see TemplateOptions#blockOnPort(int, int)
       */
      public static SoftLayerTemplateOptions blockOnPort(int port, int seconds) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.blockOnPort(port, seconds));
      }

      /**
       * @see TemplateOptions#userMetadata(Map)
       */
      public static SoftLayerTemplateOptions userMetadata(Map<String, String> userMetadata) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.userMetadata(userMetadata));
      }

      /**
       * @see TemplateOptions#userMetadata(String, String)
       */
      public static SoftLayerTemplateOptions userMetadata(String key, String value) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.userMetadata(key, value));
      }

      /**
       * @see TemplateOptions#nodeNames(Iterable)
       */
      public static SoftLayerTemplateOptions nodeNames(Iterable<String> nodeNames) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.nodeNames(nodeNames));
      }

      /**
       * @see TemplateOptions#networks(Iterable)
       */
      public static SoftLayerTemplateOptions networks(Iterable<String> networks) {
         SoftLayerTemplateOptions options = new SoftLayerTemplateOptions();
         return SoftLayerTemplateOptions.class.cast(options.networks(networks));
      }
   }

   // methods that only facilitate returning the correct object type

   /**
    * @see TemplateOptions#blockOnPort(int, int)
    */
   @Override
   public SoftLayerTemplateOptions blockOnPort(int port, int seconds) {
      return SoftLayerTemplateOptions.class.cast(super.blockOnPort(port, seconds));
   }

   /**
    * @see TemplateOptions#inboundPorts(int...)
    */
   @Override
   public SoftLayerTemplateOptions inboundPorts(int... ports) {
      return SoftLayerTemplateOptions.class.cast(super.inboundPorts(ports));
   }

   /**
    * @see TemplateOptions#authorizePublicKey(String)
    */
   @Override
   public SoftLayerTemplateOptions authorizePublicKey(String publicKey) {
      return SoftLayerTemplateOptions.class.cast(super.authorizePublicKey(publicKey));
   }

   /**
    * @see TemplateOptions#installPrivateKey(String)
    */
   @Override
   public SoftLayerTemplateOptions installPrivateKey(String privateKey) {
      return SoftLayerTemplateOptions.class.cast(super.installPrivateKey(privateKey));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public SoftLayerTemplateOptions userMetadata(Map<String, String> userMetadata) {
      return SoftLayerTemplateOptions.class.cast(super.userMetadata(userMetadata));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public SoftLayerTemplateOptions userMetadata(String key, String value) {
      return SoftLayerTemplateOptions.class.cast(super.userMetadata(key, value));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public SoftLayerTemplateOptions nodeNames(Iterable<String> nodeNames) {
      return SoftLayerTemplateOptions.class.cast(super.nodeNames(nodeNames));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public SoftLayerTemplateOptions networks(Iterable<String> networks) {
      return SoftLayerTemplateOptions.class.cast(super.networks(networks));
   }
}
