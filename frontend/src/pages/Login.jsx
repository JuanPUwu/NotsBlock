import IconLogo from "../assets/img/NotsBlockIcon.png";

export default function Login() {
  return (
    <main className="bg-animated w-full h-full flex flex-col items-center justify-center">
      <form
        action=""
        className="bg-white w-90 sm:w-2/3 lg:w-[30%] lg:min-w-xl h-3/4 sm:h-[82%] max-h-[44rem] rounded-xl shadow-xl p-10 pt-20 flex flex-col justify-between"
      >
        <div className="w-full h-auto flex flex-col">
          <div className="w-full h-16 flex flex-row items-center justify-center gap-2">
            <img src={IconLogo} alt="NotsBlock Logo" className="h-full" />
            <h1 className="text-center text-5xl">NotsBlock</h1>
          </div>
          <h2 className="text-center text-xl text-gray-400">Bienvenido</h2>
        </div>

        {/* Campos email y contraseña */}
        <div className="w-full h-auto flex flex-col gap-2">
          <label htmlFor="email" className="text-gray-400 ">
            Email
          </label>
          <input
            type="email"
            id="email"
            placeholder="example@gmail.com"
            className="w-full h-12 border border-gray-200 rounded-xl p-3 mb-1"
          />
          <label htmlFor="password" className="text-gray-400">
            Contraseña
          </label>
          <input
            type="password"
            id="password"
            className="w-full h-12 border border-gray-200 rounded-xl p-3 mb-2"
            placeholder=" * * * * * * * *"
          />

          {/* Has olvidado tu contraseña? */}
          <a
            href="/forgot-password"
            className="text-blue-500 hover:underline text-right cursor-pointer"
          >
            ¿Has olvidado tu contraseña?
          </a>
        </div>
        <div className="w-full h-auto flex flex-col gap-4">
          <button className="bg-blue-500 text-white h-12 rounded-xl cursor-pointer">
            Ingresar
          </button>
          <span className="text-center text-gray-400">
            ¿No tienes cuenta?{" "}
            <a
              href="/register"
              className="text-blue-500 hover:underline cursor-pointer"
            >
              Regístrate
            </a>
          </span>
        </div>
      </form>
      <footer className="flex flex-row w-full absolute bottom-0 justify-center lg:justify-start lg:pl-1 text-center text-white">
        Desarrollado por {""}
        <a
          href="https://github.com/JuanPUwu"
          target="_blank"
          rel="noopener noreferrer"
          className="hover:underline cursor-pointer"
        >
          @JuanPUwu
        </a>
      </footer>
    </main>
  );
}
