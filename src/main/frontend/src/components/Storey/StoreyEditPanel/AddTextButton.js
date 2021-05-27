import { useState } from "react"

const AppendTextButton = ({ appendTextToStorey }) => {
  const [text, setText] = useState("")

  const createStoreyTextForm = async () => {
    const formData = new FormData()
    formData.append("text", text)

    appendTextToStorey(formData)
  }

  const onSubmit = (e) => {
    e.preventDefault()

    if (!text) {
      alert("Please add text to your storey!")

      return
    }

    createStoreyTextForm({ text })
    setText("")
  }

  return (
    <form className="add-form" onSubmit={onSubmit}>
      <div className="form-control">
        <label>Append text: </label>
        <input
          type="text"
          placeholder="Add a description to your images here!"
          value={text}
          onChange={(e) => setText(e.target.value)}
        />
        <input type="submit" value="Save text entry" className="btn btn-block"></input>
      </div>
    </form>
  )
}

export default AppendTextButton
