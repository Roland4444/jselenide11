var div = document.createElement('h5');
div.innerText=document.documentElement.outerHTML;
div.className='dumpinfo';
document.body.insertBefore(div, document.body.firstChild);

