(let g = (fun f x => (f (fn y => y)))
in (g (fn z => z)))