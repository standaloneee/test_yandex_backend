package ru.stand.contest.restapi_yandex.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.lang.Nullable;
import ru.stand.contest.restapi_yandex.model.SystemItemType;
import ru.stand.contest.restapi_yandex.interfaces.EnumNamePattern;

import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * SystemItemImport
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-09T13:45:12.975114900+03:00[Europe/Moscow]")
@AllArgsConstructor
public class SystemItemImportDto {

  @JsonProperty("id")
  @NotNull
  private UUID id;

  @JsonProperty("url")
  @Nullable
  @Length(max = 255)
  private String url;

  @JsonProperty("parentId")
  @Nullable
  private UUID parentId;

  @JsonProperty("type")
  @EnumNamePattern(regexp = "FILE|FOLDER")
  private SystemItemType type;

  @JsonProperty("size")
  @Nullable
  @Positive
  private Long size;

  public SystemItemImportDto id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Уникальный идентфикатор
   * @return id
  */
//  @NotNull
  @Schema(name = "id", example = "элемент_1_1", description = "Уникальный идентфикатор", required = true)
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public SystemItemImportDto url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Ссылка на файл. Для папок поле равнно null.
   * @return url
  */

  @Schema(name = "url", description = "Ссылка на файл. Для папок поле равнно null.", required = false)
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public SystemItemImportDto parentId(UUID parentId) {
    this.parentId = parentId;
    return this;
  }

  /**
   * id родительской папки
   * @return parentId
  */

  @Schema(name = "parentId", example = "элемент_1_1", description = "id родительской папки", required = false)
  public UUID getParentId() {
    return parentId;
  }

  public void setParentId(UUID parentId) {
    this.parentId = parentId;
  }

  public SystemItemImportDto type(SystemItemType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull @Valid
  @Schema(name = "type", required = true)
  public SystemItemType getType() {
    return type;
  }

  public void setType(SystemItemType type) {
    this.type = type;
  }

  public SystemItemImportDto size(Long size) {
    this.size = size;
    return this;
  }

  /**
   * Целое число, для папок поле должно содержать null.
   * @return size
  */

  @Schema(name = "size", description = "Целое число, для папок поле должно содержать null.", required = false)
  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemItemImportDto systemItemImportDto = (SystemItemImportDto) o;
    return Objects.equals(this.id, systemItemImportDto.id) &&
        Objects.equals(this.url, systemItemImportDto.url) &&
        Objects.equals(this.parentId, systemItemImportDto.parentId) &&
        Objects.equals(this.type, systemItemImportDto.type) &&
        Objects.equals(this.size, systemItemImportDto.size);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url, parentId, type, size);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SystemItemImport {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

