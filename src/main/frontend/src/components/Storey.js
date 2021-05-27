import axios from "axios"
import StoreyEditPanel from "./Storey/StoreyEditPanel"
import StoreyElements from "./Storey/StoreyElements"
import { useParams } from "react-router-dom"
import { useState, useEffect } from "react"

const Storey = () => {
  const { id } = useParams()
  const [storeyElements, setStoreyElements] = useState([])

  useEffect(() => {
    document.title = id

    const getStoreyElements = async () => {
      const res = await axios.get(`http://localhost:8080/api/storey/${id}/elements`)
      setStoreyElements(res.data)
    }

    getStoreyElements()
  }, [id])

  const appendTextToStorey = async (formData) => {
    axios
      .post(`http://localhost:8080/api/storey/${id}/uploadText`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        setStoreyElements([
          ...storeyElements,
          {
            type: "TEXT_BLOCK",
            contents: res.data,
          },
        ])
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const appendImageToStorey = async (formData) => {
    axios
      .post(`http://localhost:8080/api/storey/${id}/uploadImage`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        setStoreyElements([
          ...storeyElements,
          {
            type: "IMAGE_URI",
            contents: res.data,
          },
        ])
      })
      .catch((err) => {
        console.log(err)
      })
  }

  return (
    <div>
      <StoreyElements id={id} storeyElements={storeyElements} />
      <StoreyEditPanel
        id={id}
        appendTextToStorey={appendTextToStorey}
        appendImageToStorey={appendImageToStorey}
      />
    </div>
  )
}

export default Storey
