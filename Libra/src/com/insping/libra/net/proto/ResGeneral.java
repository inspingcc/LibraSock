// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protos/libra_res_general.proto

package com.insping.libra.net.proto;

public final class ResGeneral {
  private ResGeneral() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface GeneralDataOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.GeneralData)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 protocolID = 1;</code>
     */
    int getProtocolID();

    /**
     * <code>int32 resultCode = 2;</code>
     */
    int getResultCode();

    /**
     * <code>string desc = 3;</code>
     */
    java.lang.String getDesc();
    /**
     * <code>string desc = 3;</code>
     */
    com.google.protobuf.ByteString
        getDescBytes();
  }
  /**
   * <pre>
   * 通用消息回复
   * </pre>
   *
   * Protobuf type {@code proto.GeneralData}
   */
  public  static final class GeneralData extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.GeneralData)
      GeneralDataOrBuilder {
    // Use GeneralData.newBuilder() to construct.
    private GeneralData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private GeneralData() {
      protocolID_ = 0;
      resultCode_ = 0;
      desc_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private GeneralData(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              protocolID_ = input.readInt32();
              break;
            }
            case 16: {

              resultCode_ = input.readInt32();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              desc_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.insping.libra.net.proto.ResGeneral.internal_static_proto_GeneralData_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.insping.libra.net.proto.ResGeneral.internal_static_proto_GeneralData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.insping.libra.net.proto.ResGeneral.GeneralData.class, com.insping.libra.net.proto.ResGeneral.GeneralData.Builder.class);
    }

    public static final int PROTOCOLID_FIELD_NUMBER = 1;
    private int protocolID_;
    /**
     * <code>int32 protocolID = 1;</code>
     */
    public int getProtocolID() {
      return protocolID_;
    }

    public static final int RESULTCODE_FIELD_NUMBER = 2;
    private int resultCode_;
    /**
     * <code>int32 resultCode = 2;</code>
     */
    public int getResultCode() {
      return resultCode_;
    }

    public static final int DESC_FIELD_NUMBER = 3;
    private volatile java.lang.Object desc_;
    /**
     * <code>string desc = 3;</code>
     */
    public java.lang.String getDesc() {
      java.lang.Object ref = desc_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        desc_ = s;
        return s;
      }
    }
    /**
     * <code>string desc = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDescBytes() {
      java.lang.Object ref = desc_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        desc_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (protocolID_ != 0) {
        output.writeInt32(1, protocolID_);
      }
      if (resultCode_ != 0) {
        output.writeInt32(2, resultCode_);
      }
      if (!getDescBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, desc_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (protocolID_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, protocolID_);
      }
      if (resultCode_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, resultCode_);
      }
      if (!getDescBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, desc_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.insping.libra.net.proto.ResGeneral.GeneralData)) {
        return super.equals(obj);
      }
      com.insping.libra.net.proto.ResGeneral.GeneralData other = (com.insping.libra.net.proto.ResGeneral.GeneralData) obj;

      boolean result = true;
      result = result && (getProtocolID()
          == other.getProtocolID());
      result = result && (getResultCode()
          == other.getResultCode());
      result = result && getDesc()
          .equals(other.getDesc());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + PROTOCOLID_FIELD_NUMBER;
      hash = (53 * hash) + getProtocolID();
      hash = (37 * hash) + RESULTCODE_FIELD_NUMBER;
      hash = (53 * hash) + getResultCode();
      hash = (37 * hash) + DESC_FIELD_NUMBER;
      hash = (53 * hash) + getDesc().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.insping.libra.net.proto.ResGeneral.GeneralData parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.insping.libra.net.proto.ResGeneral.GeneralData prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * 通用消息回复
     * </pre>
     *
     * Protobuf type {@code proto.GeneralData}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.GeneralData)
        com.insping.libra.net.proto.ResGeneral.GeneralDataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.insping.libra.net.proto.ResGeneral.internal_static_proto_GeneralData_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.insping.libra.net.proto.ResGeneral.internal_static_proto_GeneralData_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.insping.libra.net.proto.ResGeneral.GeneralData.class, com.insping.libra.net.proto.ResGeneral.GeneralData.Builder.class);
      }

      // Construct using com.insping.libra.net.proto.ResGeneral.GeneralData.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        protocolID_ = 0;

        resultCode_ = 0;

        desc_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.insping.libra.net.proto.ResGeneral.internal_static_proto_GeneralData_descriptor;
      }

      public com.insping.libra.net.proto.ResGeneral.GeneralData getDefaultInstanceForType() {
        return com.insping.libra.net.proto.ResGeneral.GeneralData.getDefaultInstance();
      }

      public com.insping.libra.net.proto.ResGeneral.GeneralData build() {
        com.insping.libra.net.proto.ResGeneral.GeneralData result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.insping.libra.net.proto.ResGeneral.GeneralData buildPartial() {
        com.insping.libra.net.proto.ResGeneral.GeneralData result = new com.insping.libra.net.proto.ResGeneral.GeneralData(this);
        result.protocolID_ = protocolID_;
        result.resultCode_ = resultCode_;
        result.desc_ = desc_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.insping.libra.net.proto.ResGeneral.GeneralData) {
          return mergeFrom((com.insping.libra.net.proto.ResGeneral.GeneralData)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.insping.libra.net.proto.ResGeneral.GeneralData other) {
        if (other == com.insping.libra.net.proto.ResGeneral.GeneralData.getDefaultInstance()) return this;
        if (other.getProtocolID() != 0) {
          setProtocolID(other.getProtocolID());
        }
        if (other.getResultCode() != 0) {
          setResultCode(other.getResultCode());
        }
        if (!other.getDesc().isEmpty()) {
          desc_ = other.desc_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.insping.libra.net.proto.ResGeneral.GeneralData parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.insping.libra.net.proto.ResGeneral.GeneralData) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int protocolID_ ;
      /**
       * <code>int32 protocolID = 1;</code>
       */
      public int getProtocolID() {
        return protocolID_;
      }
      /**
       * <code>int32 protocolID = 1;</code>
       */
      public Builder setProtocolID(int value) {
        
        protocolID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 protocolID = 1;</code>
       */
      public Builder clearProtocolID() {
        
        protocolID_ = 0;
        onChanged();
        return this;
      }

      private int resultCode_ ;
      /**
       * <code>int32 resultCode = 2;</code>
       */
      public int getResultCode() {
        return resultCode_;
      }
      /**
       * <code>int32 resultCode = 2;</code>
       */
      public Builder setResultCode(int value) {
        
        resultCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 resultCode = 2;</code>
       */
      public Builder clearResultCode() {
        
        resultCode_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object desc_ = "";
      /**
       * <code>string desc = 3;</code>
       */
      public java.lang.String getDesc() {
        java.lang.Object ref = desc_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          desc_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string desc = 3;</code>
       */
      public com.google.protobuf.ByteString
          getDescBytes() {
        java.lang.Object ref = desc_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          desc_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string desc = 3;</code>
       */
      public Builder setDesc(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        desc_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string desc = 3;</code>
       */
      public Builder clearDesc() {
        
        desc_ = getDefaultInstance().getDesc();
        onChanged();
        return this;
      }
      /**
       * <code>string desc = 3;</code>
       */
      public Builder setDescBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        desc_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:proto.GeneralData)
    }

    // @@protoc_insertion_point(class_scope:proto.GeneralData)
    private static final com.insping.libra.net.proto.ResGeneral.GeneralData DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.insping.libra.net.proto.ResGeneral.GeneralData();
    }

    public static com.insping.libra.net.proto.ResGeneral.GeneralData getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GeneralData>
        PARSER = new com.google.protobuf.AbstractParser<GeneralData>() {
      public GeneralData parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new GeneralData(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<GeneralData> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GeneralData> getParserForType() {
      return PARSER;
    }

    public com.insping.libra.net.proto.ResGeneral.GeneralData getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_GeneralData_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_GeneralData_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036protos/libra_res_general.proto\022\005proto\"" +
      "C\n\013GeneralData\022\022\n\nprotocolID\030\001 \001(\005\022\022\n\nre" +
      "sultCode\030\002 \001(\005\022\014\n\004desc\030\003 \001(\tB=\n\033com.insp" +
      "ing.libra.net.protoB\nResGeneral\252\002\021LibraC" +
      "lient.protob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_proto_GeneralData_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_GeneralData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_GeneralData_descriptor,
        new java.lang.String[] { "ProtocolID", "ResultCode", "Desc", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
