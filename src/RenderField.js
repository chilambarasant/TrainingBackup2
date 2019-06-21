import React from "react";
import Input from "@material-ui/core/Input";

const RenderField = ({
  input,
  label,
  name,
  className,
  placeholder,
  type,
  id,
  autoComplete,
  ref,
  autoFocus,
  meta: { touched, error, warning }
}) => {
  return (
    <div>
      <Input style={{padding: "6px 0 10px"}}
        {...input}
        id={id}
        name={name}
        type={type}
        placeholder={placeholder}
        label={label}
        autoComplete={autoComplete}
        ref={ref}
        autoFocus={autoFocus}
        fullWidth
      />
{/*      
      {touched &&
        ((error && <span>{error}</span>) ||
          (warning && <span>{warning}</span>))} */}
    </div>
  );
};
export default RenderField;
