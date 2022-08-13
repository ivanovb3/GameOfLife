import { useState, useRef, useEffect } from 'react';
import './App.css';
import axios from 'axios';

function App() {

  const [grid, setGrid] = useState([]);
  const [gridSize, setGridSize] = useState(20);
  const elementRef = useRef(null);

  const generateGrid = (size) => {
    const rows = new Array(size).fill(0)
    for (let i = 0; i < size; i++) {
      rows[i] = new Array(size);
      for(let j = 0; j < size; j++){
        rows[i][j] = 0;
      }
    }
    return rows;
  }

  //used to generate array once on mount
  useEffect(() => {
    setGrid(generateGrid(20))
  }, [elementRef.current]);

  const handleCellClick = (e) => {
    console.log(grid)
    e.preventDefault();
    const val = e.target.value;
    const pos = val.split(",")
    let currentState = grid[pos[0]][pos[1]]
    let toChange = 0
    if (currentState === 0) {
      toChange = 1;
    }
    else {
      toChange = 0;
    }
    const copy = [...grid];
    copy[pos[0]][pos[1]] = toChange
    setGrid(copy)
  }

  const handleChangeSize = (e) => {
    e.preventDefault();
    let size = e.target.value.replace(/\D/g, '');
    if (size > 50) {
      size = 50
    }
    setGridSize(size);
    setGrid(generateGrid(size));
  }

  const handlePlayButton = () =>{
    axios.post('http://localhost:8080/game',grid).then(res => {
      setGrid(res.data)
    })
  }

  return (
    <div>
      <div className="grid">
        <span className='gridSize'>
          <p style={{marginRight: '10px'}}>Specify grid size (max 50)</p>
          <input type="text" placeholder="Specify size of grid" value={gridSize} onChange={handleChangeSize}></input>
        </span>
        <br></br>
        <button className='playBtn' onClick={handlePlayButton}>Play Game Of Life</button>

        {grid.map((item, row) => {
          return (
            <div>
              {grid.map((subItem, col) => {
                return <button onClick={handleCellClick} key={[row, col]} className="cell" value={[row, col]} style={{ backgroundColor: grid[row][col] !== 1 ? 'white' : 'black' }}></button>
              })}
            </div>

          )
        })}
      </div>
    </div>
  );
}

export default App;
