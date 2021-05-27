import ImageElement from "./StoreyElements/ImageElement"
import TextBlockElement from "./StoreyElements/TextBlockElement"

const StoreyElements = ({ id, storeyElements }) => {
  if (storeyElements.length === 0) {
    return (
      <div>
        <h3>Your storey is currently empty!</h3>
        <p>Begin uploading images and adding text blocks using the editing form below.</p>
      </div>
    )
  } else {
    return storeyElements.map((storeyElement) => {
      switch (storeyElement.type) {
        case "IMAGE_URI":
          return <ImageElement parentStoreyId={id} imageFilename={storeyElement.contents} />
        case "TEXT_BLOCK":
          return <TextBlockElement textBlock={storeyElement.contents} />
        default:
          return null
      }
    })
  }
}

export default StoreyElements
