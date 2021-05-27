import { useState } from "react"

const AppendImageButton = ({ appendImageToStorey }) => {
  const [file, setFile] = useState([])

  const handleImageChange = (e) => {
    e.preventDefault()

    setFile(e.target.files[0])
  }

  const onSubmit = (e) => {
    e.preventDefault()

    const formData = new FormData()
    formData.append("file", file)

    appendImageToStorey(formData)
  }

  return (
    <div>
      <form onSubmit={(e) => onSubmit(e)}>
        <label>Append an image: </label>
        <input className="fileInput" type="file" onChange={(e) => handleImageChange(e)} />
        <button className="submitButton" type="submit" onClick={(e) => onSubmit(e)}>
          Upload Image
        </button>
      </form>
    </div>
  )
}

export default AppendImageButton
