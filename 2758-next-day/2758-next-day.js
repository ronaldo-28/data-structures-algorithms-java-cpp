Date.prototype.nextDay = function() {
  const currentDate = new Date(this.getTime());
  currentDate.setDate(currentDate.getDate() + 1);

  const year = currentDate.getFullYear();
  const month = String(currentDate.getMonth() + 1).padStart(2, '0');
  const day = String(currentDate.getDate()).padStart(2, '0');

  return `${year}-${month}-${day}`;

}

/**
 * const date = new Date("2014-06-20");
 * date.nextDay(); // "2014-06-21"
 */