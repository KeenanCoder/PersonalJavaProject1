const megaBoardEl = document.getElementById('megaBoard');
let currentPlayer = 'X';

// builds the 9 mini-boards, each with 9 cells
//uses 2 loops
for (let mainRow = 0; mainRow < 3; mainRow++) {
  for (let mainCol = 0; mainCol < 3; mainCol++) {
    const miniBoardEl = document.createElement('div');
    miniBoardEl.className = 'mini-board';
    miniBoardEl.dataset.mainRow = mainRow;
    miniBoardEl.dataset.mainCol = mainCol;

    for (let miniRow = 0; miniRow < 3; miniRow++) {
      for (let miniCol = 0; miniCol < 3; miniCol++) {
        const cellEl = document.createElement('div');
        cellEl.className = 'cell';
        cellEl.dataset.mainRow = mainRow;
        cellEl.dataset.mainCol = mainCol;
        cellEl.dataset.miniRow = miniRow;
        cellEl.dataset.miniCol = miniCol;

        cellEl.addEventListener('click', handleCellClick);
        miniBoardEl.appendChild(cellEl);
      }
    }

    megaBoardEl.appendChild(miniBoardEl);
  }
}

//calls the backend
async function handleCellClick(e){
  const cell = e.target;

  if(cell.classList.contains('taken')) return;

  const{ mainRow, mainCol, miniRow, miniCol} = cell.dataset;

  try{
    const response = await fetch('http://localhost:8080/api/move', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        mainRow: Number(mainRow),
        mainCol: Number(mainCol),
        miniRow: Number(miniRow),
        miniCol: Number(miniCol),
        player: currentPlayer
      })
    });

    const data = await response.json();

    if(data.success){
      cell.textContent = currentPlayer;
      cell.classList.add('taken');

      document.getElementById('status').textContent =
  data.gameOver
    ? (data.winner.trim() ? `${data.winner} wins!` : "It's a draw!")
    : '';
      currentPlayer = currentPlayer === 'X' ? 'O' : 'X' //swaps turns
    }
    //when the move does not work
    else {
      document.getElementById('status').textContent = 'Invalid move - try again!';
    }
  }
  //when it feels to reach the backend it will display
  //Could not connect to server
  catch (err){
    console.error('Failed to reach backend:', err);
    document.getElementById('status').textContent = 'Could not connect to server';
  }
}