const ImageElement = ({ parentStoreyId, imageFilename }) => {
  return (
    <div>
      <img
        src={`http://localhost:8080/api/i/${parentStoreyId}/${imageFilename}`}
        alt={`Missing file ${imageFilename}`}
      />
    </div>
  )
}

export default ImageElement
