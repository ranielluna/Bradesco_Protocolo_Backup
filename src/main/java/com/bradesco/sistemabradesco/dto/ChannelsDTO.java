package com.bradesco.sistemabradesco.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import com.bradesco.sistemabradesco.models.Channels;

@Schema(description = "Dados para criar um canal")
public class ChannelsDTO{
    private int code;

    @Schema(description = "Nome do canal", example = "Plantão do consumidor")
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