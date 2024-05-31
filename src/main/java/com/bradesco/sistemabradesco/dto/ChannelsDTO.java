package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;


import com.bradesco.sistemabradesco.models.Channels;


public class ChannelsDTO{
    private int code;
    private String channel;

  public ChannelsDTO(){

  }

 public ChannelsDTO(Channels channel){
     BeanUtils.copyProperties(channel, this);
 }


   //GETTERS AND SETTERS
   public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }


}