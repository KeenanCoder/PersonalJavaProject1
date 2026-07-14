const megaBoardEl = document.getElementById('megaBoard');

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

//handles a cell click (placeholder for now — wired to backend later)
function handleCellClick(e) {
  const { mainRow, mainCol, miniRow, miniCol } = e.target.dataset;
  console.log('Clicked:', mainRow, mainCol, miniRow, miniCol);

  // later: send this to /api/move via fetch, then re-render using the response
}