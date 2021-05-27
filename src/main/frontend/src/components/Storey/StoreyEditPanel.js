import AppendTextButton from "./StoreyEditPanel/AddTextButton"
import AppendImageButton from "./StoreyEditPanel/AddImageButton"

const StoreyEditPanel = ({ id, appendTextToStorey, appendImageToStorey }) => {
  return (
    <div>
      <AppendImageButton id={id} appendImageToStorey={appendImageToStorey} />
      <AppendTextButton id={id} appendTextToStorey={appendTextToStorey} />
    </div>
  )
}

export default StoreyEditPanel
