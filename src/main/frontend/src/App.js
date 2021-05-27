import CreateStoreyButton from "./components/CreateStoreyButton"

function App() {
  document.title = "imgstorey"

  const createStorey = async () => {
    const res = await fetch(`http://localhost:8080/api/storey/create`)
    const data = await res.json()

    window.open(`http://localhost:3000/${data.id}`)
  }

  return (
    <div className="App">
      <h1>Welcome to imgstorey!</h1>
      <CreateStoreyButton onClick={createStorey} />
    </div>
  )
}

export default App
